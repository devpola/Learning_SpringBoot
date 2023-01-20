package com.example.validation.validator;

import com.example.validation.annotation.YearMonth;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//AssertTrue / False를 활용하여 메서드 자체에서 custom logic을 적용할 수 있는 방법은 재활용할 수 없음
//아래와 같이 ConstraintValidator를 구현하여 custom annotation을 구현하면 재사용할 수 있음
public class YearMonthValidator implements ConstraintValidator<YearMonth, String> {

    private String pattern;

    @Override
    public void initialize(YearMonth constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //yyyyMM
        try{
            //yyyyMM으로만 검색하면 error 발생
            //그리하여 yyyyMMdd 패턴을 사용하는 대신, value 뒤에 01을 dd 부분의 default 값으로 붙여주어 검색
            LocalDate localDate = LocalDate.parse(value+"01", DateTimeFormatter.ofPattern(this.pattern));
        } catch (Exception e){
            return false;
        }
        return true;
    }
}
