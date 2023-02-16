
package com.numeryx.product.mapper;
import com.numeryx.product.dto.ProductDto;
import com.numeryx.product.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN,
        uses = {})
public interface ProductMapper extends com.numeryx.product.mapper.Mapper<ProductDto, Product> {
}

