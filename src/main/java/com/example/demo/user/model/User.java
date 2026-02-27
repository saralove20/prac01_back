package com.example.demo.user.model;

import com.example.demo.board.model.Board;
import com.example.demo.relation.model.B;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String email;
    private String name;
    @Setter
    private String password;
    @Setter
    private boolean enable;
    @ColumnDefault(value="ROLE_USER")
    private String role;

//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY /* fetch = FetchType.EAGER*/)
//    private List<Board> boardList;
}
