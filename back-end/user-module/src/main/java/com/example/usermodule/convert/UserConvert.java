package com.example.usermodule.convert;

import com.example.usermodule.pojo.User;
import com.example.usermodule.controller.user.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    @Mapping(target = "state", ignore = true)
    @Mapping(target = "id", ignore = true)
    User userVo2User(UserVo userVo);
}
