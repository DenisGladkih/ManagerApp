package net.nomia.pos.ui.onboarding.domain.repo

import com.apollographql.apollo.api.Input
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.transformLatest
import net.nomia.common.data.model.Organization
import net.nomia.erp.api.ErpClientService
import net.nomia.erp.apiFlow
import net.nomia.erp.mutation.CreateStoreV2Mutation
import net.nomia.erp.query.GetAllOrganizationsQuery
import net.nomia.erp.query.GetCatalogQuery
import net.nomia.erp.schema.type.PageRequestInput
import net.nomia.erp.schema.type.StoreSurveyStoreService
import net.nomia.erp.schema.type.StoreSurveyStoreType
import net.nomia.erp.schema.type.StoreType
import net.nomia.main.domain.model.Auth
import net.nomia.main.domain.toDomain
import net.nomia.pos.ui.onboarding.data.repo.RemoteOnboardingRepository
import net.nomia.pos.ui.onboarding.model.Resource
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class RemoteOnboardingRepositoryImpl @Inject constructor(
    private val erpClientService: ErpClientService,
) : RemoteOnboardingRepository {

    override fun saveManagerData(auth: Auth): Flow<Resource<Nothing>> = flow {
        emit(Resource.Loading())

        val organizationId = getOrganizations(auth = auth)
            .mapNotNull { resource ->
                resource.data?.firstOrNull()?.id
            }
            .first()

        val catalogId = getCatalogId(auth = auth, organizationId = organizationId)
            .mapNotNull { resource ->
                resource.data
            }
            .first()

        val client = erpClientService.principalClient(
            token = auth.accessToken,
            refreshToken = auth.refreshToken,
            organizationId = organizationId,
        ).catch {
            emit(Resource.Error(message = it.message))
        }
            .filterNotNull()
            .first()

        client.mutate(
            CreateStoreV2Mutation(
                organizationId = organizationId.value,
                catalogId = catalogId,
                type = StoreType.RESTAURANT,
                currency = "RUB",
                name = "тестовое имя",
                phone = Input.absent(),
                organizationName = Input.optional("тестовая организация"),
                address = Input.optional("тестовый адрес"),
                previousErp = Input.absent(),
                storeTypes = listOf(
                    StoreSurveyStoreType.BAR,
                    StoreSurveyStoreType.CAFE
                ),
                totalArea = Input.optional(1),
                publicArea = Input.optional(2),
                kitchenArea = Input.optional(3),
                seatingCapacity = Input.optional(3),
                storeServices = listOf(StoreSurveyStoreService.DELIVERY)
            )
        )
            .apiFlow()
            .catch { e ->
                emit(Resource.Error(e.message))
            }

        emit(Resource.Success())
    }

    override fun getOrganizations(auth: Auth): Flow<Resource<List<Organization>?>> =
        erpClientService.principalClient(
            token = auth.accessToken,
            refreshToken = auth.refreshToken,
        ).transformLatest { client ->
            client.query(
                GetAllOrganizationsQuery(
                    PageRequestInput(
                        page = 0,
                        size = 100,
                    )
                )
            )
                .apiFlow()
                .catch { e ->
                    emit(Resource.Error(message = e.message))
                }
                .mapLatest { response ->
                    response.data
                        ?.allOrganizationsPageable
                        ?.content
                        ?.map { content -> content.fragments.organizationFragment.toDomain() }
                }
                .filterNotNull()
                .collectLatest { listOrganizations ->
                    emit(Resource.Success(listOrganizations))
                }
        }


    override fun getCatalogId(
        auth: Auth,
        organizationId: Organization.ID
    ): Flow<Resource<UUID?>> = erpClientService.principalClient(
        token = auth.accessToken,
        refreshToken = auth.refreshToken,
        organizationId = organizationId,
    ).transformLatest { client ->
        client.query(GetCatalogQuery())
            .apiFlow()
            .catch { e ->
                emit(Resource.Error(message = e.message))
            }
            .collectLatest { response ->
                val data = response.data?.catalogByOrganization?.id
                emit(Resource.Success(data = data))
            }
    }
}
