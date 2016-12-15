package de.adorsys.smartbanking.tenant;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkroleRepository extends MongoRepository<Workrole, ObjectId> {

}
