package exm.algo.LinkedList;

public class DeleteDuplicates {

    /**
     *
     * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
     *
     * 示例 1:
     *
     * 输入: 1->1->2
     * 输出: 1->2
     * 示例 2:
     *
     * 输入: 1->1->2->3->3
     * 输出: 1->2->3
     */
    public ListNode deleteDuplicates(ListNode head) {
        // 双指针，当当前节点与后继节点相同，next指针往后走，当不同值，跳过值相同部分，再将curr与next指针同步
        if (head == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = head, next = head;
        while (curr != null && next.next != null) {
            if (curr.val == next.next.val) {
                next = next.next;
            } else {
                curr.next = next.next;
                curr = curr.next;
                next = curr;
            }
        }
        curr.next = null;
        return dummy.next;
    }


    /**
     * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
     *
     * 示例 1:
     *
     * 输入: 1->2->3->3->4->4->5
     * 输出: 1->2->5
     * 示例 2:
     *
     * 输入: 1->1->1->2->3
     * 输出: 2->3
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode curr = head, next = head;
        while (curr != null && next.next != null) {
            if (curr.val == next.next.val) {
                next = next.next;
            } else {
                // 这里的if-else处理pre指针指向不重复的元素，若在遍历过程中指向重复，后续遍历到不重复的节点对重复的节点进行覆盖
                if (curr == next) {
                    pre = pre.next;
                } else {
                    pre.next = next.next;
                }
                curr = next.next;
                next = curr;
            }
        }
        pre.next = curr == next ? curr : null;
        return dummy.next;
    }
}
