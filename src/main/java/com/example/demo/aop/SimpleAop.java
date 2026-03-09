package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect     // 관점 : 흩어진 관심사를 하나로 묶은 것
@Component
public class SimpleAop {

    // 실행될 위치나 시점을 지정 (아래는 위치를 지정함)
    //      * 리턴 타입 (모든 리턴 타입 해당)
    //      com.example.demo.board.. 해당 패키지 및 하위 모든 패키지 포함
    //      *.*(..) 모든 클래스의 모든 메소드 및 모든 매개변수
    @Pointcut("execution(* com.example.demo.board..*.*(..))")
    private void cut() {    // 포인트 컷을 적용할 이름을 설정, 실제 실행되는 메소드는 아님, 위의 어노테이션을 매번 쓰는게 번거로우니까 cut이라는 이름으로 쓰고 싶어서 만든 메소드
        System.out.println("컷");
    }

    // 메소드가 실행될 때, 끝날 때를 지정하고 싶으면? -> 어노테이션 사용
    // 만약 위에서 포인트컷을 안 만들어 뒀다면
    // @Before("execution(* com.example.demo.board..*.*(..))")      // PointCut을 안 만들어두면 이렇게 설정했어야 함 (매번)
    @Before("cut()")    // 포인트 컷에서 지정한 위치의 클래스의 메소드가 실행되기 전에 현재 메소드 실행
    public void before(JoinPoint joinpoint) {       // 위치나 시점을 매개변수로 전달해줌, 특정 클래스, 특정 메소드라는 정보가 들어옴
        Method method = ((MethodSignature) joinpoint.getSignature()).getMethod();

        System.out.println(joinpoint.getSignature());           // 메소드 시그니처 출력해보기
        System.out.println(method.getName() + "메소드 실행 전");  // 메소드 이름 출력해보기
    }

    @After("cut()")     // 포인트 컷에서 지정한 위치의 클래스의 메소드가 실행된 후에 현재 메소드 실행
    public void after(JoinPoint joinpoint) {       // 위치나 시점을 매개변수로 전달해줌, 특정 클래스, 특정 메소드라는 정보가 들어옴
        Method method = ((MethodSignature) joinpoint.getSignature()).getMethod();

        System.out.println(joinpoint.getSignature());           // 메소드 시그니처 출력해보기
        System.out.println(method.getName() + "메소드 실행 후");  // 메소드 이름 출력해보기
    }
}
