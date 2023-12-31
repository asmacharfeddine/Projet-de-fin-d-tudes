package iss4u.ehr.backoffice.parameterization.material_resources.services;

import iss4u.ehr.backoffice.parameterization.material_resources.entities.RoomGrp;
import iss4u.ehr.backoffice.parameterization.material_resources.entities.Room;

import java.util.List;
import java.util.Optional;

public interface RoomGrpService {
    void create(RoomGrp roomGrp);

    List<RoomGrp> retrieveRoomGroups();

    Optional<RoomGrp> getRoomGrpByKy(Long roomGrpKy);

    RoomGrp getRoomGrpByNm(String roomGrpNm);

    String getRoomGrpNameByKey(Long roomGrpKy);

    void update(Long roomGrpKy, RoomGrp updatedRoomGrp);

    void delete(Long RoomGrpKy);

    List<Room> getChildElements(RoomGrp roomGrp);

    void addChildElement(RoomGrp roomGrp, Room room);
}
