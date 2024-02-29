package OnetoOneProject.SpringBootApi.Services;

import OnetoOneProject.SpringBootApi.Models.Items;
import OnetoOneProject.SpringBootApi.Repositories.ItemRepositories;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServices {
    private final ItemRepositories itemRepositories ;

    @Autowired
    public ItemServices(ItemRepositories itemRepositories) {
        this.itemRepositories = itemRepositories;
    }
    @Transactional
    public void save(Items item){
        itemRepositories.save(item);
    }
    @Transactional
    public void deleteItem(Items item){
        itemRepositories.delete(item);
    }
    @Transactional
    public Items showOne(int id){
        Optional<Items> foundItem = itemRepositories.findById(id);
        return foundItem.orElse(null);
    }
    @Transactional
    public List<Items> findAllItems(){
        return itemRepositories.findAll();
    }
}
