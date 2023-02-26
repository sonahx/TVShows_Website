package TVShows.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data @Builder @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class User implements UserDetails{

	private static final long serialVersionUID = -421092218247224768L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String username;
	@Column
	private String email;
	@Column
	private String password;
	@Enumerated(EnumType.STRING)
	private Role roles;
	 @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Builder.Default
	private Set<UsersShows> shows = new HashSet<>();
	 
	   public List<String> getRoleList(){
	        if(this.roles.name().length() > 0){
	            return Arrays.asList(this.roles.name().split(","));
	        }
	        return new ArrayList<>();
	    }
	
	  @Override
	  public Collection<? extends GrantedAuthority> getAuthorities() {
		  Set<GrantedAuthority> authorities = new HashSet<>();
		  getRoleList().forEach(r -> {
	            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + r);
	            authorities.add(authority);
		   });
		  return authorities;
	  }

	  @Override
	  public String getPassword() {
	    return password;
	  }

	  @Override
	  public String getUsername() {
	    return email;
	  }

	  @Override
	  public boolean isAccountNonExpired() {
	    return true;
	  }

	  @Override
	  public boolean isAccountNonLocked() {
	    return true;
	  }

	  @Override
	  public boolean isCredentialsNonExpired() {
	    return true;
	  }

	  @Override
	  public boolean isEnabled() {
	    return true;
	  }
}
