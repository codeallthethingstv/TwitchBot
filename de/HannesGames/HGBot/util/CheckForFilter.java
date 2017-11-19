package de.HannesGames.HGBot.util;

import de.HannesGames.HGBot.util.data.Filters;
import de.HannesGames.HGBot.util.data.Logger;

public class CheckForFilter {
    Logger log = new Logger();

    public CheckForFilter(String msg) {
        if (Filters.getFilters() >= 17) {
            log.setLog("nothing to filter");
        } else {
            log.setLog("something here to filter");
        }
    }
}
