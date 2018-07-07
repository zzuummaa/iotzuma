package ru.zuma.iotzuma;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.zuma.iotzuma.model.IOTInputData;
import ru.zuma.iotzuma.model.IOTOutputData;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class IOTDataController {
    @RequestMapping(value = "data", method = RequestMethod.PUT)
    public void uploadData(@RequestBody IOTInputData data, HttpServletResponse response) {
        if (data.getType() == null | data.getValue() == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        IOTDataService.getInstance().add(data);
    }

    @RequestMapping(value = "data", method = RequestMethod.GET)
    public ResponseEntity<List<IOTOutputData>> getData(@RequestParam String type) {
        if (type == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<IOTOutputData> data = IOTDataService.getInstance().get(type);

        if (data != null) {
            return new ResponseEntity<>(data, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/")
    public String greeting(@RequestParam String type, Model model) {
        if (type == null) {
            //response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        model.addAttribute("type", type);
        return "plot_graph";
    }
}
