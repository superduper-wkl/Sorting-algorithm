package com.sort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/**
 * ������
 * @author wkl
 *
 */
public class Sort {
	
	public static final int begin = 1;
	
	// �������1~50
	public static final int MAXSIZE = 20;

	// ��ӡ������
	public static void print(String title, SqlList list) {
		if (title == null) {
			title = "";
		}
		System.out.println(title);
		for (int i = begin; i < list.length; i++) {
			System.out.print(list.array[i] + " ");
		}
		System.out.println();
		System.out.println();
	}
	
	// ������Ӧ�±��ֵ
	public static void swap(SqlList list, int l, int s) {
		int temp = list.array[l];
		list.array[l] = list.array[s];
		list.array[s] = temp;
	}
	
	// ���±�Ϊ0��Ϊ�ڱ�����ʱ����
	public static SqlList preSort(SqlList list) {
		SqlList temp = new SqlList(new int[list.length + 1]);
		for (int i = 0; i < list.length; i++) {
			temp.array[i + 1] = list.array[i];
		}
		return temp;
	}
	
	// ѡ������
	public static void selectSort(SqlList list) {
		for (int i = begin; i < list.length - 1; i++) {
			int temp = i;
			for (int j = i + 1; j < list.length; j++) {
				if (list.array[j] < list.array[temp]) {
					temp = j;
				}
			}
			if (temp != i) {
				swap(list, i, temp);
			}
		}
		
		print("ѡ������", list);
	}
	
	// ð������
	public static void bubbleSort(SqlList list) {
		for (int i = begin; i < list.length - 1; i++) {
			for (int j = i + 1; j < list.length; j++) {
				if (list.array[i] > list.array[j]) {
					swap(list, i, j);
				}
			}
		}
		
		print("ð������", list);
	}
	
	// �鲢����
	public static void mergeSort(SqlList list) {
		// ����������
		mSort(list, 1, list.length - 1);
		
		print("�鲢����", list);
	}
	
	/**
	 * ���������Ҷ԰뻮�֣����ϲ���һ��
	 * @param list ���������
	 * @param low ��С�±�
	 * @param high ���С��
	 */
	public static void mSort(SqlList list, int low, int high) {
		// ���ֵ�
		int mid = (low + high) / 2;
		if (low < high) {
			// ������
			mSort(list, low, mid);
			// ������
			mSort(list, mid + 1, high);
			// �ϲ�
			merge(list, low, mid, high);
		}
	}
	
	/**
	 * ���鲢���ֵ����н��кϲ�
	 * @param list ���������
	 * @param low �˴κϲ�����С�±�
	 * @param mid �˴κϲ��Ļ��ֵ�
	 * @param high �˴κϲ�������±�
	 */
	public static void merge(SqlList list, int low, int mid, int high) {
		// ��ʱ����
		SqlList temp = new SqlList(new int[high - low + 1]);
		// �����ʼ�±�
		int i = low;
		// �Ҳ���ʼ�±�
		int j = mid + 1;
		// ��ʱ������ʼ�±�
		int k = 0;
		
		// ���������а��մ�С�����˳�򿽱�����ʱ����
		while (i <= mid && j <= high) {
			if (list.array[i] < list.array[j]) {
				temp.array[k++] = list.array[i++];
			} else {
				temp.array[k++] = list.array[j++];
			}
		}
		
		// �����ʣ������п�������ʱ����
		while (i <= mid) {
			temp.array[k++] = list.array[i++];
		}
		
		// ���Ҳ�ʣ������п�������ʱ����
		while (j <= high) {
			temp.array[k++] = list.array[j++];
		}
		
		// ����ʱ�����е����ݸ��ǵ�����������
		for (int k2 = 0; k2 < temp.length; k2++) {
			list.array[k2 + low] = temp.array[k2];
		}
	}
	
	// ��������
	public static void quickSort(SqlList list) {
		qSort(list, begin, list.length - 1);
		
		print("��������", list);
	}
	
	public static void qSort(SqlList list, int low, int high) {
		// ����ֵ
		int pivot;
		if (low < high) {
			pivot = partition(list, low, high);
			qSort(list, low, pivot - 1);
			qSort(list, pivot + 1, high);
		}
	}
	
	public static int partition(SqlList list, int low, int high) {
		int pivotkey;
		
		pivotkey = list.array[low];
		while (low < high) {
			while (low < high && list.array[high] >= pivotkey) {
				high--;
			}
			swap(list, low, high);
			
			while (low < high && list.array[low] <= pivotkey) {
				low++;
			}
			swap(list, low, high);
		}
		
		return low;
	}
	
	public static void main(String[] args) throws Exception {
		
		File file = new File("data/test.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String temp;
		int[] a = new int[MAXSIZE];
		for (int i = 0; (temp = br.readLine()) != null && i < MAXSIZE; i++) {
			a[i] = Integer.parseInt(temp);
		}
		
		br.close();
		
		SqlList list1 = new SqlList(a.clone());
		list1 = preSort(list1);
		// ����ǰ
		print("����ǰ", list1);
		// ѡ������
		selectSort(list1);
		
		SqlList list2 = new SqlList(a.clone());
		list2 = preSort(list2);
		// ����ǰ
		print("����ǰ", list2);
		// ð������
		bubbleSort(list2);
		
		SqlList list3 = new SqlList(a.clone());
		list3 = preSort(list3);
		// ����ǰ
		print("����ǰ", list3);
		// �鲢����
		mergeSort(list3);
		
		SqlList list4 = new SqlList(a.clone());
		list4 = preSort(list4);
		// ����ǰ
		print("����ǰ", list4);
		// ��������
		quickSort(list4);
		
	}
	
	/**
	 * �����װ������
	 * @author wkl
	 *
	 */
	static class SqlList {
		
		public int[] array;
		
		public int length;

		public SqlList() {}

		public SqlList(int[] array) {
			this.array = array;
			this.length = array.length;
		}
	}
	
}
