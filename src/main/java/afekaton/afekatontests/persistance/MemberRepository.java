package afekaton.afekatontests.persistance;

import afekaton.afekatontests.models.members.Member;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ps3to_000 on 04-Mar-18.
 */
public interface MemberRepository extends CrudRepository<Member, Integer> {
    public Member findByName(String name);
}
