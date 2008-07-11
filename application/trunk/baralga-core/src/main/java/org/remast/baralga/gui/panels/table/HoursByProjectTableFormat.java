package org.remast.baralga.gui.panels.table;

import org.remast.baralga.Messages;
import org.remast.baralga.gui.model.report.HoursByProject;

import ca.odell.glazedlists.gui.TableFormat;

/**
 * @author remast
 */
public class HoursByProjectTableFormat implements TableFormat<HoursByProject> {

    /**
     * Gets the number of columns for the table.
     */
    public int getColumnCount() {
        return 2;
    }

    /**
     * Gets the name of the given column.
     * @param column the number of the column
     */
    public String getColumnName(int column) {
        switch (column) {
        case 0:
            return Messages.getString("HoursByDayProjectFormat.ProjectHeading"); //$NON-NLS-1$
        case 1:
            return Messages.getString("HoursByDayProjectFormat.HoursHeading"); //$NON-NLS-1$
        default:
            return null;
        }
    }

    public Object getColumnValue(HoursByProject baseObject, int column) {
        switch (column) {
        case 0:
            return baseObject.getProject();
        case 1:
            return baseObject.getHours();
        default:
            return null;
        }
    }

}
