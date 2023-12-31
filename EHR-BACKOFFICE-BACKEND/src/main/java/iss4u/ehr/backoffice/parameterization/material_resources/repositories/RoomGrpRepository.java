package iss4u.ehr.backoffice.parameterization.material_resources.repositories;

import iss4u.ehr.backoffice.parameterization.material_resources.entities.RoomGrp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomGrpRepository extends JpaRepository<RoomGrp, Long> {
    RoomGrp findByRoomGrpNm(String roomGrpNm);
}
