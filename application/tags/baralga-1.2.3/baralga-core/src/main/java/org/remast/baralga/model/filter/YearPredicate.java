package org.remast.baralga.model.filter;

import java.util.Date;

import org.apache.commons.collections.Predicate;
import org.remast.baralga.Messages;
import org.remast.baralga.model.ProjectActivity;
import org.remast.util.DateUtils;

public class YearPredicate implements Predicate {

    private final Date dateInYear;

    public YearPredicate(final Date dateInYear) {
        this.dateInYear = dateInYear;
    }

    public boolean evaluate(Object object) {
        if (object == null) {
            return false;
        }

        if (!(object instanceof ProjectActivity)) {
            throw new IllegalArgumentException(Messages
                    .getString("YearPredicate.ErrorNoProjectActivity")); //$NON-NLS-1$
        }

        final ProjectActivity activity = (ProjectActivity) object;
        return DateUtils.isSameYear(activity.getStart(), this.dateInYear);
    }

}