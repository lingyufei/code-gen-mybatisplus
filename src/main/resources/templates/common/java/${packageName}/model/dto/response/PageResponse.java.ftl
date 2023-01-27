package ${packageName}.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
* 分页dto
* @author ${author}
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    //总记录数
    private long totalCount;
    //每页记录数
    private int pageSize;
    //总页数
    private int totalPage;
    //当前页数
    private int currentPage;

    private Boolean desc;
    //列表数据
    private List<T> list;

    /**
    * 分页
    * @param list        列表数据
    * @param totalCount  总记录数
    * @param pageSize    每页记录数
    * @param currPage    当前页数
    * @param desc    排序
    */
    public PageResponse(List<T> list, long totalCount, int pageSize, int currPage, Boolean desc) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currentPage = currPage;
        this.totalPage = (int)Math.ceil((double)totalCount/pageSize);
        this.desc = desc;
    }
}