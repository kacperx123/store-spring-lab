package pl.wsei.storespring.dto;

import pl.wsei.storespring.model.Basket;
import pl.wsei.storespring.model.Item;

import java.util.List;

public class BasketDTO {

	private Long id;
	private List<Item> items;
	private Long promotionId;

	public BasketDTO() {}

	public BasketDTO(Long id, List<Item> items, Long promotionId) {
		this.id = id;
		this.items = items;
		this.promotionId = promotionId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	public static BasketDTO fromEntity(Basket basket) {
		return new BasketDTO(
				basket.getId(),
				basket.getItems(),
				basket.getPromotion() != null ? basket.getPromotion().getId() : null
		);
	}

	public static Basket toEntity(BasketDTO dto) {
		Basket basket = new Basket();
		basket.setId(dto.getId());
		basket.setItems(dto.getItems());
		return basket;
	}
}

