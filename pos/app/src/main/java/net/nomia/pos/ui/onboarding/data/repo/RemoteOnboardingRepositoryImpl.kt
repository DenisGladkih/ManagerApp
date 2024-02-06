package net.nomia.pos.ui.onboarding.data.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import net.nomia.common.data.model.Organization
import net.nomia.erp.api.ErpClientService
import net.nomia.erp.apiFlow
import net.nomia.erp.mutation.SaveManagerDataMutation
import net.nomia.erp.query.GetAllOrganizationsQuery
import net.nomia.erp.query.GetCatalogQuery
import net.nomia.erp.schema.type.PageRequestInput
import net.nomia.main.domain.model.Auth
import net.nomia.main.domain.toDomain
import net.nomia.pos.ui.onboarding.domain.repo.RemoteOnboardingRepository
import net.nomia.pos.ui.onboarding.domain.toCreateStoreInput
import net.nomia.pos.ui.onboarding.domain.model.ManagerData
import net.nomia.pos.ui.onboarding.domain.model.Resource
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class RemoteOnboardingRepositoryImpl @Inject constructor(
    private val erpClientService: ErpClientService,
) : RemoteOnboardingRepository {
    override fun saveManagerData(
        auth: Auth?,
        managerData: ManagerData,
    ): Flow<Resource<Nothing>> = flow {

        emit(Resource.Loading)

        val organizationId = getOrganizations(auth = auth)
            .map { organizations ->
                organizations?.firstOrNull()?.id ?: error("organization id is null")
            }
            .catch { e ->
                emit(Resource.Error(e.message))
            }
            .first()

        val catalogId = getCatalogId(auth = auth, organizationId = organizationId)
            .map { uuid ->
                uuid ?: error("catalog id is null")
            }
            .catch { e ->
                emit(Resource.Error(e.message))
            }
            .first()

        val client = erpClientService.principalClient(
            token = auth?.accessToken,
            refreshToken = auth?.refreshToken,
            organizationId = organizationId,
        )
            .firstOrNull()
            ?: error("client is null")

        client.mutate(
            SaveManagerDataMutation(
                input = managerData.toCreateStoreInput(
                    organizationId = organizationId.value,
                    catalogId = catalogId,
                ),
            ),
        )
            ?.apiFlow()
            ?.catch { e ->
                emit(Resource.Error(e.message))
            }
            ?.collect {
                emit(Resource.Success())
            }
    }

    override fun getOrganizations(auth: Auth?): Flow<List<Organization>?> = erpClientService
        .principalClient(
            token = auth?.accessToken,
            refreshToken = auth?.refreshToken,
        )
        .flatMapLatest { client ->
            client.query(
                GetAllOrganizationsQuery(
                    PageRequestInput(
                        page = 0,
                        size = 100,
                    )
                )
            )
                .apiFlow()
                .mapLatest { response ->
                    response.data
                        ?.allOrganizationsPageable
                        ?.content
                        ?.map { content -> content.fragments.organizationFragment.toDomain() }

                }
        }

    override fun getCatalogId(
        auth: Auth?,
        organizationId: Organization.ID,
    ): Flow<UUID?> = erpClientService
        .principalClient(
            token = auth?.accessToken,
            refreshToken = auth?.refreshToken,
            organizationId = organizationId,
        )
        .flatMapLatest { client ->
            client.query(GetCatalogQuery())
                .apiFlow()
                .mapLatest { response ->
                    response.data?.catalogByOrganization?.id
                }
        }
}
