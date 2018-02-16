package Utils;

import Entity.Bet;
import Entity.Partie;

import Services.PartieService;
import Services.ServiceBet;
import javafx.collections.ObservableList;

public class Pari {

    public String matchParier(int idPartie) {
        String match = "";
        PartieService partiService = new PartieService();
        ServiceBet betService = new ServiceBet();

        int idHome = partiService.geIdHome(idPartie);
        int idAway = partiService.geIdAway(idPartie);
        String nomHome = partiService.getNomEquipe(idHome);
        String nomAway = partiService.getNomEquipe(idAway);

        match = nomHome + "   VS   " + nomAway;

        return match;
    }

    public String matchDateTour(int idPartie) {
        PartieService partieService = new PartieService();
        Partie partie = new Partie();
        partie = partieService.DetailsPartie(idPartie);
        String dateTour = partie.getTour()+"  /   "+partie.getDatematch();

        return dateTour;
    }

}
