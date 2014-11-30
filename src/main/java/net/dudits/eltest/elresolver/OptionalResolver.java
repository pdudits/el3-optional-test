package net.dudits.eltest.elresolver;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.el.LambdaExpression;
import java.beans.FeatureDescriptor;
import java.util.Iterator;
import java.util.Optional;

/**
 * Support for {@link Optional} in EL.
 * @author Patrik Dudits
 */
public class OptionalResolver extends ELResolver {

    @Override
    public Object invoke(ELContext context, Object base, Object method, Class<?>[] paramTypes, Object[] params) {
        if (base != null && base instanceof Optional && params.length == 1 && params[0] instanceof LambdaExpression) {
            LambdaExpression lambda = (LambdaExpression) params[0];
            Optional<?> o = (Optional<?>) base;
            if ("map".equals(method)) {
                context.setPropertyResolved(true);
                Object result = o.map(e -> lambda.invoke(context, e));
                return result;
            } else if ("flatMap".equals(method)) {
                context.setPropertyResolved(true);
                Optional<?> result = o.flatMap(e -> (Optional<?>)lambda.invoke(context, e));
                return result;
            }
        }
        return super.invoke(context, base, method, paramTypes, params);
    }

    @Override
    public Object getValue(ELContext context, Object base, Object property) {
        // pass everything except the Optional's only property - present
        if (base instanceof Optional && !"present".equals(property)) {
            Optional optional = (Optional) base;
            if (optional.isPresent()) {
                Object value = context.getELResolver().getValue(context, optional.get(), property);
                // Make the return value a flat Optional
                if (value instanceof Optional) {
                    return value;
                } else {
                    return Optional.of(value);
                }
            } else {
                context.setPropertyResolved(true);
                return Optional.empty();
            }
        }
        return null;
    }

    @Override
    public Class<?> getType(ELContext context, Object base, Object property) {
        return null;
    }

    @Override
    public void setValue(ELContext context, Object base, Object property, Object value) {
         if (base instanceof Optional) {
             if (((Optional) base).isPresent()) {
                 context.getELResolver().setValue(context, ((Optional) base).get(), property, value);
             } else {
                 context.setPropertyResolved(true);
             }
         }
    }

    @Override
    public boolean isReadOnly(ELContext context, Object base, Object property) {
        return false;
    }

    @Override
    public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext context, Object base) {
        return null;
    }

    @Override
    public Class<?> getCommonPropertyType(ELContext context, Object base) {
        return null;
    }
}
