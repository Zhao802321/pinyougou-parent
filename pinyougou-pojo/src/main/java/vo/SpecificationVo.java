package vo;

import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationOption;

import java.io.Serializable;
import java.util.List;

public class SpecificationVo implements Serializable{

    private TbSpecification specification;
    private List<TbSpecificationOption> specificationOption;

    public TbSpecification getSpecification() {
        return specification;
    }

    public void setSpecification(TbSpecification specification) {
        this.specification = specification;
    }

    public List<TbSpecificationOption> getSpecificationOption() {
        return specificationOption;
    }

    public void setSpecificationOption(List<TbSpecificationOption> specificationOption) {
        this.specificationOption = specificationOption;
    }
}
