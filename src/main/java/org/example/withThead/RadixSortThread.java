package org.example.withThead;

import org.example.withoutThead.RadixSort;

public class RadixSortThread extends Thread{
    private int[] internal;

    public RadixSortThread(int[] arr) {
        internal = arr;
    }

    public int[] getInternal() {
        return internal;
    }

    @Override
    public void run() {
        RadixSort.radixSort(internal);
    }
}
