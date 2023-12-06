package coding.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import coding.test.entity.Member;

public interface MemberQuery extends JpaRepository<Member, String> {

	@Query("SELECT m.email FROM Member m WHERE m.email = :insertEmail")
	String selectEmail(@Param("insertEmail") String insertEmail);

	// 이전 쿼리 메서드들을 그대로 유지

	@Query("SELECT p.itemcount FROM Book p WHERE p.itemcount = :productId")
	Long selectItemCount(@Param("productId") Long productId);

	@Query("SELECT p.description FROM Book p WHERE p.itemcount = :productId")
	String selectDescription(@Param("productId") Long productId);

	@Query("SELECT p.img FROM Book p WHERE p.itemcount = :productId")
	String selectImg(@Param("productId") Long productId);

	

	@Query("SELECT p.author FROM Book p WHERE p.itemcount = :productId")
	String selectAuthor(@Param("productId") Long productId);
	@Query("SELECT p.code FROM Book p WHERE p.itemcount = :productId")
	String selectCode(@Param("productId") Long productId);

	@Query("SELECT p.date FROM Book p WHERE p.itemcount = :productId")
	String selectDate(@Param("productId") Long productId);

	@Query("SELECT p.title FROM Book p WHERE p.itemcount = :productId")
	String selectTitle(@Param("productId") Long productId);

	@Query("SELECT p.category FROM Book p WHERE p.itemcount = :productId")
	String selectCategory(@Param("productId") Long productId);

	@Query("SELECT m.name FROM Member m WHERE m.name = :insertName")
	String selectNameByName(@Param("insertName") String insertName);

	@Query("SELECT m.phone FROM Member m WHERE m.phone= :insertMobile")
	String selectMobile(@Param("insertMobile") String insertMobile);

	@Query("SELECT m.email FROM Member m WHERE m.phone= :insertMobile")
	String selectMobilebyEmail(@Param("insertMobile") String insertMobile);
}
