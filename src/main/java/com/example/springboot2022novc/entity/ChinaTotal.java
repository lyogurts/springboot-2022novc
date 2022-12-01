package com.example.springboot2022novc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

@TableName("china_total")
public class ChinaTotal {
    @TableId(
            value = "id",
            type = IdType.AUTO
    )
    private Integer id;
    private Integer confirm;
    private Integer input;
    private Integer severe;
    private Integer heal;
    private Integer dead;
    private Integer suspect;
    private Date updateTime;

    public ChinaTotal() {
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getConfirm() {
        return this.confirm;
    }

    public Integer getInput() {
        return this.input;
    }

    public Integer getSevere() {
        return this.severe;
    }

    public Integer getHeal() {
        return this.heal;
    }

    public Integer getDead() {
        return this.dead;
    }

    public Integer getSuspect() {
        return this.suspect;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public void setConfirm(final Integer confirm) {
        this.confirm = confirm;
    }

    public void setInput(final Integer input) {
        this.input = input;
    }

    public void setSevere(final Integer severe) {
        this.severe = severe;
    }

    public void setHeal(final Integer heal) {
        this.heal = heal;
    }

    public void setDead(final Integer dead) {
        this.dead = dead;
    }

    public void setSuspect(final Integer suspect) {
        this.suspect = suspect;
    }

    public void setUpdateTime(final Date updateTime) {
        this.updateTime = updateTime;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ChinaTotal)) {
            return false;
        } else {
            ChinaTotal other = (ChinaTotal)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label107: {
                    Object this$id = this.getId();
                    Object other$id = other.getId();
                    if (this$id == null) {
                        if (other$id == null) {
                            break label107;
                        }
                    } else if (this$id.equals(other$id)) {
                        break label107;
                    }

                    return false;
                }

                Object this$confirm = this.getConfirm();
                Object other$confirm = other.getConfirm();
                if (this$confirm == null) {
                    if (other$confirm != null) {
                        return false;
                    }
                } else if (!this$confirm.equals(other$confirm)) {
                    return false;
                }

                Object this$input = this.getInput();
                Object other$input = other.getInput();
                if (this$input == null) {
                    if (other$input != null) {
                        return false;
                    }
                } else if (!this$input.equals(other$input)) {
                    return false;
                }

                label86: {
                    Object this$severe = this.getSevere();
                    Object other$severe = other.getSevere();
                    if (this$severe == null) {
                        if (other$severe == null) {
                            break label86;
                        }
                    } else if (this$severe.equals(other$severe)) {
                        break label86;
                    }

                    return false;
                }

                label79: {
                    Object this$heal = this.getHeal();
                    Object other$heal = other.getHeal();
                    if (this$heal == null) {
                        if (other$heal == null) {
                            break label79;
                        }
                    } else if (this$heal.equals(other$heal)) {
                        break label79;
                    }

                    return false;
                }

                label72: {
                    Object this$dead = this.getDead();
                    Object other$dead = other.getDead();
                    if (this$dead == null) {
                        if (other$dead == null) {
                            break label72;
                        }
                    } else if (this$dead.equals(other$dead)) {
                        break label72;
                    }

                    return false;
                }

                Object this$suspect = this.getSuspect();
                Object other$suspect = other.getSuspect();
                if (this$suspect == null) {
                    if (other$suspect != null) {
                        return false;
                    }
                } else if (!this$suspect.equals(other$suspect)) {
                    return false;
                }

                Object this$updateTime = this.getUpdateTime();
                Object other$updateTime = other.getUpdateTime();
                if (this$updateTime == null) {
                    if (other$updateTime != null) {
                        return false;
                    }
                } else if (!this$updateTime.equals(other$updateTime)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ChinaTotal;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $id = this.getId();
         result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $confirm = this.getConfirm();
        result = result * 59 + ($confirm == null ? 43 : $confirm.hashCode());
        Object $input = this.getInput();
        result = result * 59 + ($input == null ? 43 : $input.hashCode());
        Object $severe = this.getSevere();
        result = result * 59 + ($severe == null ? 43 : $severe.hashCode());
        Object $heal = this.getHeal();
        result = result * 59 + ($heal == null ? 43 : $heal.hashCode());
        Object $dead = this.getDead();
        result = result * 59 + ($dead == null ? 43 : $dead.hashCode());
        Object $suspect = this.getSuspect();
        result = result * 59 + ($suspect == null ? 43 : $suspect.hashCode());
        Object $updateTime = this.getUpdateTime();
        result = result * 59 + ($updateTime == null ? 43 : $updateTime.hashCode());
        return result;
    }

    public String toString() {
        return "ChinaTotal(id=" + this.getId() + ", confirm=" + this.getConfirm() + ", input=" + this.getInput() + ", severe=" + this.getSevere() + ", heal=" + this.getHeal() + ", dead=" + this.getDead() + ", suspect=" + this.getSuspect() + ", updateTime=" + this.getUpdateTime() + ")";
    }
}

