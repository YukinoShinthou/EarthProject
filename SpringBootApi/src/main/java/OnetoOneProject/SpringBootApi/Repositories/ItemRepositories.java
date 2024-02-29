package OnetoOneProject.SpringBootApi.Repositories;


import OnetoOneProject.SpringBootApi.Models.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepositories extends JpaRepository<Items, Integer> {

}
