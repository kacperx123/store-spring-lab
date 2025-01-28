package pl.wsei.storespring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wsei.storespring.dto.BasketDTO;
import pl.wsei.storespring.exception.ResourceNotFoundException;
import pl.wsei.storespring.model.Basket;
import pl.wsei.storespring.model.Item;
import pl.wsei.storespring.model.Promotion;
import pl.wsei.storespring.repository.BasketRepository;
import pl.wsei.storespring.repository.PromotionRepository;

import java.util.List;

@Service
public class BasketService {

	private BasketRepository basketRepository;

	@Autowired
	public BasketService(BasketRepository basketRepository) {
		this.basketRepository = basketRepository;
	}

	public Basket createBasket(BasketDTO basketDto) {
		Basket basket = BasketDTO.toEntity(basketDto);
		return basketRepository.save(basket);
	}

	public BasketDTO getBasketById(Long id) {
		return BasketDTO.fromEntity(basketRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Basket not found")));
	}

	public List<BasketDTO> getAllBaskets() {
		return basketRepository.findAll().stream().map(BasketDTO::fromEntity).toList();
	}

	public double calculateTotalPrice(Long basketId) {
		Basket basket = basketRepository.findById(basketId)
				.orElseThrow(() -> new ResourceNotFoundException("Basket not found"));

		return basket.getItems().stream()
				.mapToDouble(Item::getPrice)
				.sum();
	}

	public Basket updateBasket(Long id, BasketDTO basketDetails) {
		Basket basket = basketRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Basket not found"));
		basket.setItems(basketDetails.getItems());
		return basketRepository.save(basket);
	}

	public void deleteBasket(Long id) {
		Basket basket = basketRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Basket not found"));
		basketRepository.delete(basket);
	}

	public double calculateTotalPriceAfterPromotion(Long basketId) {
		Basket basket = basketRepository.findById(basketId)
				.orElseThrow(() -> new ResourceNotFoundException("Basket not found"));

		double totalPrice = basket.getItems().stream()
				.mapToDouble(Item::getPrice)
				.sum();

		if (basket.getPromotion() != null) {
			double discount = basket.getPromotion().getDiscountPercentage() / 100.0;
			totalPrice *= (1 - discount);
		}

		return totalPrice;
	}

	public Basket applyPromotion(Long basketId, Long promotionId, PromotionRepository promotionRepository) {
		Basket basket = basketRepository.findById(basketId)
				.orElseThrow(() -> new ResourceNotFoundException("Basket not found"));

		Promotion promotion = promotionRepository.findById(promotionId)
				.orElseThrow(() -> new ResourceNotFoundException("Promotion not found"));

		basket.setPromotion(promotion);
		return basketRepository.save(basket);
	}
}