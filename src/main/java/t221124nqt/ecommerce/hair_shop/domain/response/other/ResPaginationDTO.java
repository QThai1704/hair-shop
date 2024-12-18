package t221124nqt.ecommerce.hair_shop.domain.response.other;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResPaginationDTO {
    private Meta meta;
    private Object data;

    @Getter
    @Setter
    public static class Meta {
        // Trang hiện tại
        private int page;
        // Số phần tử trên mỗi trang
        private int pageSize;
        // Tổng số trang
        private int pages;
        // Tổng số phần tử
        private long total;
    }

    public static ResPaginationDTO.Meta addMeta(Page<?> object, Pageable pageable){
        ResPaginationDTO.Meta meta = new ResPaginationDTO.Meta();
        meta.setPage(pageable.getPageNumber() + 1);
        meta.setPageSize(pageable.getPageSize());
        meta.setPages(object.getTotalPages());
        meta.setTotal(object.getTotalElements());
        return meta;
    }
}
