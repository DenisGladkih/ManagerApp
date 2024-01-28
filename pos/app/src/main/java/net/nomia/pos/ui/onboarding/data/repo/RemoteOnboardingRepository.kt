package net.nomia.pos.ui.onboarding.data.repo

import kotlinx.coroutines.flow.Flow
import net.nomia.common.data.model.Organization
import net.nomia.main.domain.model.Auth
import net.nomia.pos.ui.onboarding.model.Resource
import java.util.UUID

internal interface RemoteOnboardingRepository {
    fun saveManagerData(auth: Auth): Flow<Resource<Nothing>>

    fun getOrganizations(auth: Auth): Flow<Resource<List<Organization>?>>

    fun getCatalogId(auth: Auth, organizationId: Organization.ID): Flow<Resource<UUID?>>
}
