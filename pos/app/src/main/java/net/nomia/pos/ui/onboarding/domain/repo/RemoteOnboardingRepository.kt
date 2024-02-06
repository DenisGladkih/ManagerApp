package net.nomia.pos.ui.onboarding.domain.repo

import kotlinx.coroutines.flow.Flow
import net.nomia.common.data.model.Organization
import net.nomia.main.domain.model.Auth
import net.nomia.pos.ui.onboarding.domain.model.ManagerData
import net.nomia.pos.ui.onboarding.domain.model.Resource
import java.util.UUID

internal interface RemoteOnboardingRepository {
    fun saveManagerData(auth: Auth?, managerData: ManagerData): Flow<Resource<Nothing>>

    fun getOrganizations(auth: Auth?): Flow<List<Organization>?>

    fun getCatalogId(auth: Auth?, organizationId: Organization.ID): Flow<UUID?>
}
