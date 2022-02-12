package  fr.orsys.kingsley.kanbanWS.service;

import java.util.List;

import fr.orsys.kingsley.kanbanWS.business.TypeTache;
public interface TypeTacheService {

	TypeTache ajouterTypeTache(String string, String string2);

	List<TypeTache> recupererTypesTache();

	long compterTypesTache();
}
