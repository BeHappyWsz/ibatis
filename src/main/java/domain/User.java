package domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author wsz
 * @Des:
 * @Date: 19:51 2017/10/16
 */
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String realName;

    @Setter
    @Getter
    private Long age;


    @Setter
    @Getter
    private String des;


	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", realName=" + realName
				+ ", age=" + age + ", des=" + des + "]";
	}
}
