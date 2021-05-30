package com.quickjs.android;

public class JSFunction extends JSObject {

    public JSFunction(JSContext context, JavaCallback callback) {
        super(context, QuickJS._initNewJSFunction(context.getContextPtr(), callback.hashCode(), false));
        this.context.registerCallback(callback, this);
    }

    public JSFunction(JSContext context, JavaVoidCallback callback) {
        super(context, QuickJS._initNewJSFunction(context.getContextPtr(), callback.hashCode(), true));
        this.context.registerCallback(callback, this);
    }

    public JSFunction(JSContext context, long tag, int u_int32, double u_float64, long u_ptr) {
        super(context, tag, u_int32, u_float64, u_ptr);
    }

    public Object call(JSValue.TYPE type, JSObject receiver, JSArray parameters) {
        if (receiver == null) {
            receiver = JSValue.Undefined(context);
        }
        Object result = QuickJS._executeFunction2(context.getContextPtr(), type.value, receiver, this, parameters);
        return JSValue.checkType(result, type);
    }

    @Override
    public void close() {
        super.close();
        // TODO
//        context.functionRegistry.remove(this.tag);
    }
}