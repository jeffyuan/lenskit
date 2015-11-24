/*
 * LensKit, an open source recommender systems toolkit.
 * Copyright 2010-2014 LensKit Contributors.  See CONTRIBUTORS.md.
 * Work on LensKit has been funded by the National Science Foundation under
 * grants IIS 05-34939, 08-08692, 08-12148, and 10-17697.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */
package org.grouplens.lenskit.transform.normalize;

import org.grouplens.lenskit.vectors.MutableSparseVector;
import org.grouplens.lenskit.vectors.SparseVector;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Abstract item vector normalizer implementation.
 */
public abstract class AbstractItemVectorNormalizer implements ItemVectorNormalizer {

    /**
     * {@inheritDoc}
     * <p>Delegates to {@link #makeTransformation(long, SparseVector)}
     * and the resulting {@link VectorTransformation}.
     */
    @Override
    public MutableSparseVector normalize(long item, @Nonnull SparseVector vector,
                                         @Nullable MutableSparseVector target) {
        MutableSparseVector v = target;
        if (v == null) {
            v = vector.mutableCopy();
        }

        VectorTransformation tform = makeTransformation(item, vector);
        return tform.apply(v);
    }
}