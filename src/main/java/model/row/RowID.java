package model.row;

import lombok.Data;
import model.page.PageID;

import java.io.Serial;
import java.io.Serializable;

/**
 * 表中行的id，包含所在的pageID
 */
@Data
public class RowID implements Serializable {
    @Serial
    private static final long serialVersionUID = -6571929580121756569L;
    /**
     * PageID
     */
    private final PageID pageID;

    /**
     * Page中slot的index
     */
    private final int slotIndex;
}
