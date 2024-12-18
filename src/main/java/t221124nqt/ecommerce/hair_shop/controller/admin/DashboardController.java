package t221124nqt.ecommerce.hair_shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @GetMapping("/admin")
    public String getDashboard() {
        return "admin/index";
    }

    @GetMapping("/admin/staff")
    public String getStaff() {
        return "admin/staff/show";
    }
}
