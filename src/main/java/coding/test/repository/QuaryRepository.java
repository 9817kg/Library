package coding.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import coding.test.entity.Member;
import jakarta.transaction.Transactional;


public interface QuaryRepository extends JpaRepository<Member, Long> {
	@Transactional
	@Modifying
	@Query("UPDATE Member m SET m.password = :newPassword WHERE m.password = :currentPassword")
	void updatePassword(@Param("newPassword") String newPassword, @Param("currentPassword") String currentPassword);

	@Query("SELECT m.email FROM Member m WHERE m.email = :insertEmail")
	String selectEmail(@Param("insertEmail") String insertEmail);

	@Query("SELECT m.name FROM Member m WHERE m.name = :insertName")
	String selectNameByName(@Param("insertName") String insertName);

	@Transactional
    @Modifying
    @Query("UPDATE Member m SET m.password = :newPassword WHERE m.email = :newEmail ")
    void updateUserPassword(@Param("newEmail") String newEmail, @Param("newPassword") String newPassword);
	
	@Query("SELECT m.phone FROM Member m WHERE m.phone= :insertMobile")
    String selectMobile(@Param("insertMobile") String insertMobile);

    @Query("SELECT m.email FROM Member m WHERE m.phone= :insertMobile")
    String selectMobilebyEmail(@Param("insertMobile") String insertMobile);

}
