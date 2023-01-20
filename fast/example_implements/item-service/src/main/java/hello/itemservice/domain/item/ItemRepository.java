package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>();   //static
    // 멀티 스레드 환경에서 여러 개의 스레드가 동시에 위의 store에 접근하는 경우, HashMap을 사용하면 x => ConcurrentHashMap 사용해야함
    // why? ItemRepository가 sigleton으로 사용되기 때문
    private static long sequence = 0L;  //static
    // 마찬가지로, 멀티 스레드 환경에서 여러 스레드가 sequence에 동시 접근하는 경우, 값이 꼬이는 것을 방지하기 위해 atomic long 등을 사용해야함

    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id){
        return store.get(id);
    }

    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }

    //예제 프로젝트이기 때문에 아래의 형식대로 set을 해주었지만,
    //실제 프로젝트라면 ItemDto를 만들어서 name, price, quantity만 두고 사용해야한다.
    public void update(Long itemId, Item updateParam){
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore(){
        store.clear();
    }
}
