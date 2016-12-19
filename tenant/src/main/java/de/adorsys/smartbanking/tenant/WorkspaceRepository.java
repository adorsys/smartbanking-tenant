package de.adorsys.smartbanking.tenant;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkspaceRepository extends MongoRepository<Workspace, ObjectId> {

  List<Workspace> findByWorkroleId(ObjectId workroleId);
}
