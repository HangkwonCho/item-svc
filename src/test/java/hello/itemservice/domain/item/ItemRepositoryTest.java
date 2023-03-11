package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        // given
        Item item = new Item("itemA", 1000, 1);

        // when
        Item savedItem = itemRepository.save(item);

        // then
        Item findItem = itemRepository.findById(savedItem.getId());

        assertThat(findItem).isEqualTo(savedItem);

    }

    @Test
    void findAll() {
        // given
        Item itemA = new Item("itemA", 1000, 1);
        Item itemB = new Item("itemB", 2000, 3);
        itemRepository.save(itemA);
        itemRepository.save(itemB);

        // when
        List<Item> result = itemRepository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(itemA, itemB);
    }

    @Test
    void updateItem() {
        // given
        Item itemA = new Item("itemA", 1000, 1);
        Item savedItem = itemRepository.save(itemA);
        Long itemId = savedItem.getId();

        // when
        Item itemB = new Item("itemB", 4000, 3);
        itemRepository.update(itemId, itemB);

        // then
        Item findItem = itemRepository.findById(itemId);
        assertThat(findItem.getItemName()).isEqualTo(itemB.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(itemB.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(itemB.getQuantity());
    }
}