package util;
/**
 * ��װ��ҳ��Ϣ
 * @author Az.
 *
 */

import java.util.List;

public class PageModel {

	//�����
	private  List list;
	
	//��ѯ��¼��
	private int totalRecords;
	
	//ÿҳ����������
	private int pageSize;
	
	//��ǰ�ڼ�ҳ
	private int pageNo;
	
	/**
	 * ��ҳ��
	 * @return
	 */
	public int getTotalPages() {
		return (totalRecords + pageSize - 1) / pageSize;
	}
	
	/**
	 * ȡ����ҳ
	 * @return
	 */
	public int getTopPageNo() {
		return 1;
	}
	
	/**
	 * ȡ��βҳ
	 * @return
	 */
	public int getBottomPageNo() {
		return getTotalPages();
	}
	
	/**
	 * ȡ����һҳ
	 * @return
	 */
	public int getPreviousPageNo() {
		/*������룺��Ӱ�조��һҳ����ť����
		 * if (pageNo == 1) {
			pageNo = 1;
		}else {
			pageNo = pageNo - 1;
		}
		return pageNo;*/
		
		if (pageNo <= 1) {
			return 1;
		}
		return pageNo - 1;
	}
	
	/**
	 * ȡ����һҳ
	 * @return
	 */
	public int getNextPageNo() {
		if (pageNo >= getBottomPageNo()) {
			return getBottomPageNo();
		}
		return pageNo + 1;
		
	}
	
	
	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	
}
