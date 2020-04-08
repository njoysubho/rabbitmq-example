package org.example.kitchenservice.annotation;

        import java.lang.annotation.ElementType;
        import java.lang.annotation.Target;

@Target(ElementType.TYPE)
public @interface MessageQueue {
    String topic() default  "";
    String routingKey() default  "";
}
