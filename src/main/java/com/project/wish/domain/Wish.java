package com.project.wish.domain;


import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "wish")
    private List<WishHistory> wishHistories = new ArrayList<>();

    private String title;
    private String content;
    private String image;
    private String productName;
    private Long goalAmount;
    private Date goalDate;
    private boolean isPublic;
    private boolean completionStatus;
    private LocalDateTime registerDatetime;
    private LocalDateTime modifyDatetime;

    public Wish(Long id, User user, List<WishHistory> wishHistories, String title, String content, String image,
        String productName, Long goalAmount, Date goalDate, boolean isPublic, boolean completionStatus,
        LocalDateTime registerDatetime, LocalDateTime modifyDatetime) {
        this.id = id;
        this.user = user;
        this.wishHistories = wishHistories;
        this.title = title;
        this.content = content;
        this.image = image;
        this.productName = productName;
        this.goalAmount = goalAmount;
        this.goalDate = goalDate;
        this.isPublic = isPublic;
        this.completionStatus = completionStatus;
        this.registerDatetime = registerDatetime;
        this.modifyDatetime = modifyDatetime;
    }

    public Wish() {
    }

    public static WishBuilder builder() {
        return new WishBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public User getUser() {
        return this.user;
    }

    public List<WishHistory> getWishHistories() {
        return this.wishHistories;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public String getImage() {
        return this.image;
    }

    public String getProductName() {
        return this.productName;
    }

    public Long getGoalAmount() {
        return this.goalAmount;
    }

    public Date getGoalDate() {
        return this.goalDate;
    }

    public boolean isPublic() {
        return this.isPublic;
    }

    public boolean isCompletionStatus() {
        return this.completionStatus;
    }

    public LocalDateTime getRegisterDatetime() {
        return this.registerDatetime;
    }

    public LocalDateTime getModifyDatetime() {
        return this.modifyDatetime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setWishHistories(List<WishHistory> wishHistories) {
        this.wishHistories = wishHistories;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setGoalAmount(Long goalAmount) {
        this.goalAmount = goalAmount;
    }

    public void setGoalDate(Date goalDate) {
        this.goalDate = goalDate;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public void setCompletionStatus(boolean completionStatus) {
        this.completionStatus = completionStatus;
    }

    public void setRegisterDatetime(LocalDateTime registerDatetime) {
        this.registerDatetime = registerDatetime;
    }

    public void setModifyDatetime(LocalDateTime modifyDatetime) {
        this.modifyDatetime = modifyDatetime;
    }

    public String toString() {
        return "Wish(id=" + this.getId() + ", user=" + this.getUser() + ", wishHistories=" + this.getWishHistories()
            + ", title=" + this.getTitle() + ", content=" + this.getContent() + ", image=" + this.getImage()
            + ", productName=" + this.getProductName() + ", goalAmount=" + this.getGoalAmount() + ", goalDate="
            + this.getGoalDate() + ", isPublic=" + this.isPublic() + ", completionStatus=" + this.isCompletionStatus()
            + ", registerDatetime=" + this.getRegisterDatetime() + ", modifyDatetime=" + this.getModifyDatetime() + ")";
    }

    public static class WishBuilder {

        private Long id;
        private User user;
        private List<WishHistory> wishHistories;
        private String title;
        private String content;
        private String image;
        private String productName;
        private Long goalAmount;
        private Date goalDate;
        private boolean isPublic;
        private boolean completionStatus;
        private LocalDateTime registerDatetime;
        private LocalDateTime modifyDatetime;

        WishBuilder() {
        }

        public WishBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public WishBuilder user(User user) {
            this.user = user;
            return this;
        }

        public WishBuilder wishHistories(List<WishHistory> wishHistories) {
            this.wishHistories = wishHistories;
            return this;
        }

        public WishBuilder title(String title) {
            this.title = title;
            return this;
        }

        public WishBuilder content(String content) {
            this.content = content;
            return this;
        }

        public WishBuilder image(String image) {
            this.image = image;
            return this;
        }

        public WishBuilder productName(String productName) {
            this.productName = productName;
            return this;
        }

        public WishBuilder goalAmount(Long goalAmount) {
            this.goalAmount = goalAmount;
            return this;
        }

        public WishBuilder goalDate(Date goalDate) {
            this.goalDate = goalDate;
            return this;
        }

        public WishBuilder isPublic(boolean isPublic) {
            this.isPublic = isPublic;
            return this;
        }

        public WishBuilder completionStatus(boolean completionStatus) {
            this.completionStatus = completionStatus;
            return this;
        }

        public WishBuilder registerDatetime(LocalDateTime registerDatetime) {
            this.registerDatetime = registerDatetime;
            return this;
        }

        public WishBuilder modifyDatetime(LocalDateTime modifyDatetime) {
            this.modifyDatetime = modifyDatetime;
            return this;
        }

        public Wish build() {
            return new Wish(id, user, wishHistories, title, content, image, productName, goalAmount, goalDate, isPublic,
                completionStatus, registerDatetime, modifyDatetime);
        }

        public String toString() {
            return "Wish.WishBuilder(id=" + this.id + ", user=" + this.user + ", wishHistories=" + this.wishHistories
                + ", title=" + this.title + ", content=" + this.content + ", image=" + this.image + ", productName="
                + this.productName + ", goalAmount=" + this.goalAmount + ", goalDate=" + this.goalDate + ", isPublic="
                + this.isPublic + ", completionStatus=" + this.completionStatus + ", registerDatetime="
                + this.registerDatetime + ", modifyDatetime=" + this.modifyDatetime + ")";
        }
    }
}

