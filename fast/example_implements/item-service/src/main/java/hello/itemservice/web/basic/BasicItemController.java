package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    // 생성자가 한 개 밖에 없으면, Autowired annotation 생략해도 주입받을 수 있음
//    @Autowired
//    public BasicItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

    // ** RequiredArgsConstructor annotation을 사용하면, 위처럼 명시적으로 생성자를 작성할 필요 없음!!

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

//    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model){
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item";
    }

    // ModelAttribute annotation을 통해,
    // 1. Item 객체 생성 및 요청 파라미터의 값을 프로퍼티 접근법(setXXX)으로 입력해준다.
    // 2. Model에 지정한 객체를 자동으로 추가한다.
//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item){
        itemRepository.save(item);

        return "basic/item";
    }

    // addItemV2의 ModelAttribute에 전달되는 모델 명을 V3와 같이 명시하지 않으면
    // -> ModelAttribute에 해당하는 매개변수의 Class 이름에서 첫 글자만 소문자로 바꾼 것으로 사용한다.
    // V3의 경우 Item -> item으로 사용하는 것
//    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item){
        itemRepository.save(item);

        return "basic/item";
    }

    // addItemV3에서 ModelAttribute annotation까지 생략 가능
    // 생략해도 V3와 똑같은 기능 수행
//    @PostMapping("/add")
    public String addItemV4(Item item){
        itemRepository.save(item);

        return "basic/item";
    }

    // addItemV1-V4의 문제점 : 등록 후, 뷰는 세부상세페이지로 보이지만, 마지막에 서버에 전송된 데이터는 POST(/add)이기 때문에,
    // 등록 후 새로고침을 하면, 계속해서 상품이 등록된다.
    // 해결책으로 PRG(POST, Redirect, GET) 패턴이 있다.
    // V1-V4와 같이 view를 리턴하는 것이 아니라 edit 메서드와 같이 redirect를 사용하면 된다.
//    @PostMapping("/add")
    public String addItemV5(Item item){
        itemRepository.save(item);

        return "redirect:/basic/items/" + item.getId();
    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes){
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item){
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    // 테스트용 데이터 추가
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));

    }
}
