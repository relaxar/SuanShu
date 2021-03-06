/*
 * Copyright (c) Numerical Method Inc.
 * http://www.numericalmethod.com/
 * 
 * THIS SOFTWARE IS LICENSED, NOT SOLD.
 * 
 * YOU MAY USE THIS SOFTWARE ONLY AS DESCRIBED IN THE LICENSE.
 * IF YOU ARE NOT AWARE OF AND/OR DO NOT AGREE TO THE TERMS OF THE LICENSE,
 * DO NOT USE THIS SOFTWARE.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITH NO WARRANTY WHATSOEVER,
 * EITHER EXPRESS OR IMPLIED, INCLUDING, WITHOUT LIMITATION,
 * ANY WARRANTIES OF ACCURACY, ACCESSIBILITY, COMPLETENESS,
 * FITNESS FOR A PARTICULAR PURPOSE, MERCHANTABILITY, NON-INFRINGEMENT,
 * TITLE AND USEFULNESS.
 * 
 * IN NO EVENT AND UNDER NO LEGAL THEORY,
 * WHETHER IN ACTION, CONTRACT, NEGLIGENCE, TORT, OR OTHERWISE,
 * SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR
 * ANY CLAIMS, DAMAGES OR OTHER LIABILITIES,
 * ARISING AS A RESULT OF USING OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.numericalmethod.suanshu.analysis.uniroot;

import com.numericalmethod.suanshu.analysis.function.rn2r1.univariate.UnivariateRealFunction;
import com.numericalmethod.suanshu.analysis.function.polynomial.Polynomial;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Haksun Li
 */
public class NewtonTest {

    /**
     * Solve for log(x) = 0.
     */
    @Test
    public void test_Newton_0010() throws NoRootFoundException {
        UnivariateRealFunction f = new UnivariateRealFunction() {

            @Override
            public double evaluate(double x) {
                return Math.log(x);
            }
        };

        Newton instance = new Newton(1e-15, 10);
        double root = instance.solve(
                f,
                new UnivariateRealFunction() {

                    @Override
                    public double evaluate(double x) {
                        return 1d / x;
                    }
                },
                0.5);
        assertEquals(1d, root, 1e-15);
    }

    /**
     * Solve for log(x) = 0.
     */
    @Test
    public void test_Newton_0020() throws NoRootFoundException {
        UnivariateRealFunction f = new UnivariateRealFunction() {

            @Override
            public double evaluate(double x) {
                return Math.log(x);
            }
        };

        Newton instance = new Newton(1e-15, 10);
        double root = instance.solve(f, 0.5);
        assertEquals(1d, root, 1e-15);
    }

    /**
     * Solve for x^3 - 6x^2 + 11x - 6 = 0.
     * The roots are 1, 2, 3.
     */
    @Test
    public void test_Newton_0030() throws NoRootFoundException {
        UnivariateRealFunction f = new Polynomial(new double[]{1, -6, 11, -6});//p(x) = x^3 - 6x^2 + 11x - 6

        Newton instance = new Newton(1e-15, 10);
        double root = instance.solve(
                f,
                new Polynomial(new double[]{3, -12, 11}),//p(x) = 3x^2 - 12x + 11,
                0.5);
        assertEquals(1d, root, 1e-15);

        instance = new Newton(1e-15, 100);
        root = instance.solve(
                f,
                new Polynomial(new double[]{3, -12, 11}),//p(x) = 3x^2 - 12x + 11,
                1.8);
        assertEquals(2d, root, 1e-14);

        root = instance.solve(
                f,
                new Polynomial(new double[]{3, -12, 11}),//p(x) = 3x^2 - 12x + 11,
                2.8);
        assertEquals(3d, root, 1e-14);
    }

    /**
     * Solve for x^3 - 6x^2 + 11x - 6 = 0.
     * The roots are 1, 2, 3.
     */
    @Test
    public void test_Newton_0040() throws NoRootFoundException {
        UnivariateRealFunction f = new Polynomial(new double[]{1, -6, 11, -6});//p(x) = x^3 - 6x^2 + 11x - 6

        Newton instance = new Newton(1e-15, 10);

        double root = instance.solve(f, 0.5);
        assertEquals(1d, root, 1e-10);//less accurate using finite difference

        instance = new Newton(1e-15, 100);
        root = instance.solve(f, 1.8);
        assertEquals(2d, root, 1e-12);//less accurate using finite difference

        root = instance.solve(f, 2.8);
        assertEquals(3d, root, 1e-13);//less accurate using finite difference
    }
}
