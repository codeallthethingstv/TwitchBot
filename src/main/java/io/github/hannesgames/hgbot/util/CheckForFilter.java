package io.github.hannesgames.hgbot.util;

import io.github.hannesgames.hgbot.util.data.Filters;
import io.github.hannesgames.hgbot.util.data.Logger;

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
