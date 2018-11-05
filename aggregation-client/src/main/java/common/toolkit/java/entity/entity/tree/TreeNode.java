/**
 * Project Name:tailaikesms-common
 * File Name:BaseTreeNode.java
 * Package Name:com.tailaike.tree.entity
 * Date:2013年11月21日 下午2:55:30
 * Copyright (c) 2013, www.wisdombud.com All Rights Reserved.
 */

package common.toolkit.java.entity.entity.tree;

/**
 * ClassName: BaseTreeNode. <br/>
 * Function: TODO <br/>
 * date: 2013年11月21日 下午2:55:30 <br/>
 * @author ghlin
 * @version
 * @since JDK 1.6
 */
public class TreeNode implements Comparable<TreeNode> {
    public static final String ROOT_TYPE = "root";

    protected String           id;
    protected String           text;
    protected String           iconCls;
    protected Boolean          leaf;
    protected String           icon;
    protected Boolean          checked;
    protected NodeTypeEnum     operType;
    protected Integer          index;
    protected Boolean          expanded;
    protected String           cls;
    protected String           otherMsg;
    protected String           path;

    public TreeNode() {
    }

    /**
     * Creates a new instance of TreeNode.
     * @param id
     * @param text
     * @param iconCls
     * @param operType
     */

    public TreeNode(String id, String text, String iconCls, NodeTypeEnum operType) {
        this.id = id;
        this.text = text;
        this.iconCls = iconCls;
        this.operType = operType;
    }

    public TreeNode(String id, String text, String iconCls, NodeTypeEnum operType, Boolean leaf) {
        this.id = id;
        this.text = text;
        this.iconCls = iconCls;
        this.operType = operType;
        this.leaf = leaf;
    }

    // public static void main(String[] args) {
    // System.out.println(new Gson().toJson(new TreeNode("1", "text", "adfasdf", NodeTypeEnum.ROOT)));
    // System.out.println("ROOT".equals(NodeTypeEnum.ROOT.toString()));
    // }

    /**
     * id.
     * @return the id
     * @since JDK 1.6
     */
    public String getId() {
        return id;
    }

    /**
     * text.
     * @return the text
     * @since JDK 1.6
     */
    public String getText() {
        return text;
    }

    /**
     * iconCls.
     * @return the iconCls
     * @since JDK 1.6
     */
    public String getIconCls() {
        return iconCls;
    }

    /**
     * leaf.
     * @return the leaf
     * @since JDK 1.6
     */
    public Boolean getLeaf() {
        return leaf;
    }

    /**
     * checked.
     * @return the checked
     * @since JDK 1.6
     */
    public Boolean getChecked() {
        return checked;
    }

    /**
     * operType.
     * @return the operType
     * @since JDK 1.6
     */
    public NodeTypeEnum getOperType() {
        return operType;
    }

    /**
     * id.
     * @param id the id to set
     * @since JDK 1.6
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * text.
     * @param text the text to set
     * @since JDK 1.6
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * iconCls.
     * @param iconCls the iconCls to set
     * @since JDK 1.6
     */
    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    /**
     * leaf.
     * @param leaf the leaf to set
     * @since JDK 1.6
     */
    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    /**
     * checked.
     * @param checked the checked to set
     * @since JDK 1.6
     */
    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    /**
     * operType.
     * @param operType the operType to set
     * @since JDK 1.6
     */
    public void setOperType(NodeTypeEnum operType) {
        this.operType = operType;
    }

    /**
     * index.
     * @return the index
     * @since JDK 1.6
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * index.
     * @param index the index to set
     * @since JDK 1.6
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * expanded.
     * @return the expanded
     * @since JDK 1.6
     */
    public Boolean getExpanded() {
        return expanded;
    }

    /**
     * expanded.
     * @param expanded the expanded to set
     * @since JDK 1.6
     */
    public void setExpanded(Boolean expanded) {
        this.expanded = expanded;
    }

    /**
     * icon.
     * @return the icon
     * @since JDK 1.6
     */
    public String getIcon() {
        return icon;
    }

    /**
     * icon.
     * @param icon the icon to set
     * @since JDK 1.6
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }

    public String getOtherMsg() {
        return otherMsg;
    }

    public void setOtherMsg(String otherMsg) {
        this.otherMsg = otherMsg;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public int compareTo(TreeNode o) {
        if (o.getId().equals(this.id)) {
            return 0;
        } else {
            return 1;
        }
    }
}
