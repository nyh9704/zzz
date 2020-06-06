package com.wyzc.htgl.dao;

import java.util.List;

import com.bn.javax.dao.Page;
import com.wyzc.htgl.bo.WyzcPersonnelBo;
import com.wyzc.htgl.bo.WyzcPersonnelBo2;

public interface ContractDao {

    List<WyzcPersonnelBo> showContract1(WyzcPersonnelBo bo);

    void deleteShowContract(WyzcPersonnelBo2 bo2);

    void addContract(WyzcPersonnelBo2 bo2);

    List<WyzcPersonnelBo2> searchContract(WyzcPersonnelBo2 bo2);

    Integer modifyContract(WyzcPersonnelBo bo);

    List<WyzcPersonnelBo> searchContractQx(WyzcPersonnelBo bo);

}
