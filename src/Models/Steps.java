package Models;

import java.util.ArrayList;
import java.util.List;

public class Steps {
    List<Integer> list= new ArrayList<>();
    private int step_next = 0;       //步骤数组中下一步存储位置的索引

    public void add(int n){
        if(step_next<list.size()){              //清除因“上一步”操作而回退的索引之后的过时step记录
            for (int i=0; i<(list.size()-step_next); i++)
                list.remove(step_next);
        }
        list.add(n);
        step_next++;
    }

    public boolean hasPre(){            //存在上一步吗
        return step_next!=0;
    }

    public boolean hasNext(){           //存在下一步吗
        return step_next!=list.size();
    }

    public int getPre(){                //拿取上一步
        step_next = step_next-1;
        return list.get(step_next);
    }

    public int getNext() {              //拿取下一步
        step_next = step_next+1;
        return list.get(step_next-1);
    }
}
