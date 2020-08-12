package me.skiincraft.api.paladins.objects;

public class Legendary extends Card {
	
	public Legendary(Card card) {
		super(card.getName(), card.getNameEnglish(),
				card.getCardId1(), card.getCardId2(),
				card.getDescription(), card.isExclusive(),
				card.getRank(), card.getRarity(),
				card.getRechargeSeconds(), card.getMultiplicator(),
				card.getIcon());
	}

}
