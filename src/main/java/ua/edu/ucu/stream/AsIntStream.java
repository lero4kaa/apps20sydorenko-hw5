package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;

import java.util.ArrayList;

public class AsIntStream implements IntStream {

    private ArrayList<Integer> generalArray;

    private AsIntStream(int... values) {
        this.generalArray = new ArrayList<>(values.length);
        for (int value: values) {
            this.generalArray.add(value);
        }
    }

    private AsIntStream(ArrayList<Integer> values) {
        this.generalArray = new ArrayList<>(values);
    }

    public static IntStream of(int... values) {
        return new AsIntStream(values);
    }

    @Override
    public Double average() {
        checkIfEmpty(generalArray);
        return (double) sum() / count();
    }

    @Override
    public Integer max() {
        checkIfEmpty(generalArray);
        double maxValue = Double.NEGATIVE_INFINITY;
        for (int value: generalArray) {
            if (value > maxValue) {
                maxValue = value;
            }
        }
        return (int)maxValue;
    }

    @Override
    public Integer min() {
        checkIfEmpty(generalArray);
        double minValue = Double.POSITIVE_INFINITY;
        for (int value: generalArray) {
            if (value < minValue) {
                minValue = value;
            }
        }
        return (int)minValue;
    }

    @Override
    public long count() {
        checkIfEmpty(generalArray);
        return this.generalArray.size();
    }

    @Override
    public Integer sum() {
        checkIfEmpty(generalArray);
        int resSum = 0;
        for (int value: generalArray) {
            resSum += value;
        }
        return resSum;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        checkIfEmpty(generalArray);
        ArrayList<Integer> resArr = new ArrayList<Integer>();
        for (int value: this.generalArray) {
            if (predicate.test(value)) {
                resArr.add(value);
            }
        }
        return new AsIntStream(resArr);
    }

    @Override
    public void forEach(IntConsumer action) {
        checkIfEmpty(generalArray);
        for (int value: this.generalArray) {
            action.accept(value);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        checkIfEmpty(generalArray);
        ArrayList<Integer> resArr = new ArrayList<Integer>();
        for (int value: generalArray) {
            resArr.add(mapper.apply(value));
        }
        return new AsIntStream(resArr);
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        checkIfEmpty(generalArray);
        ArrayList<Integer> resArr = new ArrayList<Integer>();
        for (int value: generalArray) {
            IntStream newStream = func.applyAsIntStream(value);
            int[] newStreamArr = newStream.toArray();
            for (int element: newStreamArr) {
                resArr.add(element);
            }
        }
        return new AsIntStream(resArr);
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        checkIfEmpty(generalArray);
        int res = identity;
        for (int value: this.generalArray) {
            res = op.apply(res, value);
        }
        return res;
    }

    @Override
    public int[] toArray() {
        checkIfEmpty(generalArray);
        int[] res = new int[this.generalArray.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = generalArray.get(i);
        }
        return res;
    }

    private void checkIfEmpty(ArrayList<Integer> arrLst) {
        if (arrLst.isEmpty()) {
            throw new IllegalArgumentException("Empty array");
        }
    }

}
