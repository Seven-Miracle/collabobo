package com.sparta.collabobo.board.entity;

import com.sparta.collabobo.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Getter
@Setter
@Where(clause = "deleted_date IS NULL")
@Builder
@Table(name = "board")
@NoArgsConstructor
@AllArgsConstructor
public class BoardEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 30)
  private String title;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private String color;

  @Column(nullable = false)
  private boolean deleted = false;

  @Column(nullable = false)
  @CreatedDate
  private LocalDateTime createdDate;
  @Column
  private LocalDateTime updatedDate;
  @Column
  @LastModifiedDate
  private LocalDateTime deletedDate;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany(mappedBy = "board")
  private Set<BoardUser> collaborators = new HashSet<>();

  public boolean isValidColor(String color) {
    // 예시: 색상 코드 형식 (#FFFFFF) 검사 또는 지정된 색상 이름 검사
    String colorPattern = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";
    return color.matches(colorPattern);
  }

  public void setColor(String color) {
    if (isValidColor(color)) {
      this.color = color;
    } else {
      throw new IllegalArgumentException("잘못된 색상 입니다.");
    }
  }
}
