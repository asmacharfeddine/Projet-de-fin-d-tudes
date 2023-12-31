package iss4u.ehr.backoffice.parameterization.material_resources.repositories;

import iss4u.ehr.backoffice.parameterization.material_resources.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByRoomLabel(String roomLabel);
}
