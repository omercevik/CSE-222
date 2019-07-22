/**
 * CSE 222 Data Structures and Algorithms
 * Homework 02
 * 161044004
 * @author Omer CEVIK
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.lang.Iterable;
import java.util.NoSuchElementException;

public class Main
{
    public static class ExperimentList implements Iterable
    {
        public Experiment Head;

        /**
         * addExp inserts the Experiment that Experiment's day.
         * Index starts with 1.
         * @param E New experiment.
         */
        public void addExp(Experiment E)
        {
            Experiment head = Head;

            if (E.getDay() < head.getDay() && head.nextDay == null)
            {
                E.nextDay = head;
                E.next = head;
                head = E;
            }
            else if (E.getDay() == head.getDay() && head.nextDay == null)
            {
                while(head.next != null)
                {
                    head = head.next;
                }
                head.next = E;
            }
            else if (E.getDay() > head.getDay() && head.nextDay == null)
            {
                head.nextDay = E;
                while (head.next != null)
                {
                    head = head.next;
                }
                head.next = E;
            }
            else
            {
                Experiment head1 = Head;
                boolean flag = true;

                while(head != null)
                {
                    if (head.getDay() == E.getDay())
                    {
                        if (head.next == null)
                        {
                            head.next = E;
                        }
                        else{
                            while (head.next != null && head.next != head.nextDay) {
                                if (head.getDay() == E.getDay()) {
                                    head = head.next;
                                } else {
                                    break;
                                }
                            }
                            Experiment temp = head.next;
                            head.next = E;
                            E.next = temp;
                        }
                        flag = false;
                        break;
                    }
                    head = head.nextDay;
                }

                if (flag)
                {
                    head = head1;
                    while(head != null)
                    {
                        if (head.nextDay == null)
                        {
                            head.nextDay = E;
                            while (head.next != null)
                            {
                                head = head.next;
                            }
                            head.next = E;
                            break;
                        }
                        else if ((head.getDay() < E.getDay() && head.nextDay.getDay() > E.getDay()) ||
                                (head.getDay() < E.getDay() && head.nextDay == null))
                        {
                            System.out.println("afasfa");
                            while(head1.getDay() == head1.next.getDay())
                            {
                                head1 = head1.next;
                            }
                            E.nextDay = head.nextDay;
                            E.next = head1.next;
                            head.nextDay = E;
                            head1.next = E;
                            break;
                        }
                        head = head.nextDay;
                        head1 = head;
                    }
                }
            }
        }

        /**
         * getExp returns the Experiment given in day with index.
         * Index starts with 1.
         * @param day Get day.
         * @param index Get index of day.
         * @return Returns experiment.
         */
        public Experiment getExp(int day, int index)
        {
            System.out.println("getExp(int day, int index) method is called!");
            if ( index < 1 || day < 1 )
            {
                System.out.println("Unacceptable day or index to getExp!");
                return null;
            }
            boolean flag = false;
            Experiment head = Head;

            while(head != null)
            {
                if (head.getDay() == day)
                {
                    flag = true;
                    break;
                }
                head = head.nextDay;
            }

            if (flag)
            {
                int indexTemp = 1;
                boolean flag2 = false;
                while(head != null)
                {
                    if (index == indexTemp)
                    {
                        flag2 = true;
                        break;
                    }
                    ++indexTemp;
                    head = head.next;
                }
                if (flag2)
                {
                    return head;
                }
                else
                {
                    System.out.println("Experiment can not found in getExp method!");
                    return null;
                }
            }
            else
            {
                System.out.println("Experiment can not found in getExp method!");
                return null;
            }
        }

        /**
         * setExp sets the Experiment given in day with index.
         * Index starts with 1.
         * @param day Set day.
         * @param index Set index of day.
         * @param e Setting experiment.
         */
        public void setExp(int day, int index, Experiment e)
        {
            System.out.println("setExp(int day, int index, Experiment e) method is called!");
            if ( index < 1 || day < 1 )
            {
                System.out.println("Unacceptable day or index to setExp!");
                Head.setCompleted(false);
                System.exit(0);
            }
            if(day == Head.getDay() && index == 1)
            {
                e.next = Head.next;
                e.nextDay = Head.nextDay;
                Head = e;
            }
            else if (index == 1)
            {
                Experiment head = Head;
                while (head != null) {
                    if (head.getDay() == day) {
                        head.setSetup(e.getSetup());
                        head.setCompleted(e.getCompleted());
                        head.setAccuracy(e.getAccuracy());
                        head.setTime(e.getTime());
                        break;
                    }
                    head = head.nextDay;
                }
            }
            else {
                Experiment head = Head;
                boolean flag = false;
                while (head != null) {
                    if (head.getDay() == day) {
                        flag = true;
                        break;
                    }
                    head = head.nextDay;
                }

                if (flag) {
                    int indexTemp = 1;
                    while (head.next != head.nextDay) {
                        if (index - 1 == indexTemp) {
                            Experiment temp = head.next.next;
                            e.next = temp;
                            head.next = e;
                            break;
                        }
                        ++indexTemp;
                        head = head.next;
                    }
                } else {
                    System.out.println("Unacceptable day to setExp!");
                    System.exit(0);
                }
            }
        }

        /**
         * removeExp deletes/removes the Experiment given in day with index.
         * Index starts with 1.
         * @param day Remove day.
         * @param index Remove index of day.
         */
        public void removeExp(int day, int index)
        {
            System.out.println("removeExp(int day, int index) method is called!");
            if ( index < 1 || day < 1 )
            {
                System.out.println("Unacceptable day or index to removeExp!");
                Head.setCompleted(false);
                System.exit(0);
            }

            Experiment head = Head;

            if (index == 1)
            {
                if (head.getDay() == day)
                {
                    Experiment temp = Head.nextDay;
                    Head = Head.next;
                    Head.nextDay = temp;
                }
                else
                {
                    boolean flag = true;

                    while(head.nextDay != null)
                    {
                        Experiment head1 = head;
                        if (head.nextDay.getDay() == day )
                        {
                            while(head1.next.getDay() != head.nextDay.getDay())
                            {
                                head1 = head1.next;
                            }
                            head1.next = head.nextDay.next;
                            head.nextDay = head1.next;
                            flag = false;
                            break;
                        }
                        head = head.nextDay;
                    }

                    if (flag)
                    {
                        Experiment head1 = head;

                        while(head1.nextDay.nextDay != null)
                        {
                            head1 = head1.nextDay;
                        }
                        Experiment head2 = head1.nextDay;
                        head1.nextDay = head1.nextDay.next;
                        while(head1.next.getDay() != head2.getDay())
                        {
                            head1 = head1.next;
                        }
                        head1.next = head2.next;
                    }
                }
            }
            else
            {
                boolean flag = true;
                Experiment headTail = head;
                while(head != null)
                {
                    if(head.getDay() == day)
                    {
                        flag = false;
                        break;
                    }
                    head = head.nextDay;
                }

                if (!flag && (head.nextDay == null))
                {
                    while(headTail.nextDay != null)
                    {
                        headTail = headTail.nextDay;
                    }
                    int tempIndex = 1;
                    while(headTail.next != null)
                    {
                        if (index-1 == tempIndex)
                        {
                            headTail.next = headTail.next.next;
                            break;
                        }
                        headTail = headTail.next;
                        ++tempIndex;
                    }

                }
                else if (!flag)
                {
                    Experiment head1 = head.nextDay;
                    int tempIndex = 1;
                    while(head.next.getDay() != head1.getDay() && head.next != null)
                    {
                        if (index-1 == tempIndex)
                        {
                            head.next = head.next.next;
                            break;
                        }
                        ++tempIndex;
                        head = head.next;
                    }
                }
            }
        }

        /**
         * removeDay deletes/removes all experiments in given day.
         * @param day Remove day.
         */
        public void removeDay(int day)
        {
            System.out.println("removeDay(int day) method is called!");
            if ( day < 1 )
            {
                System.out.println("Unacceptable day or index to removeExp!");
                Head.setCompleted(false);
                System.exit(0);
            }

            Experiment head = Head;

            if (head.nextDay == null && head.getDay() == day)
            {
                Head = null;
            }
            else if (head.getDay() == day && head.nextDay != null)
            {
                Head = Head.nextDay;
            }
            else
            {
                while(head.nextDay != null)
                {
                    if (head.nextDay.getDay() == day )
                    {
                        Experiment head1 = head;

                        while(head1.next != head.nextDay)
                        {
                            head1 = head1.next;
                        }
                        head1.next = head.nextDay.nextDay;
                        head.nextDay = head.nextDay.nextDay;
                        break;
                    }
                    head = head.nextDay;
                }
            }
        }


        /**
         * listExp prints the Experiments with given day using Completed instance variable.
         * @param day List day.
         */
        public void listExp(int day)
        {
            System.out.println("listExp(int day) method is called!");
            if (day < 1)
            {
                System.out.println("Unacceptable day for listExp");
                System.exit(0);
            }
            Experiment head = Head;
            boolean flag = false;
            while(head != null)
            {
                if (head.getDay() == day)
                {
                    flag = true;
                    break;
                }
                head = head.nextDay;
            }

            if (flag)
            {
                Experiment head1 = head;
                while(head != head1.nextDay)
                {
                    if (head.getCompleted())
                    {
                        System.out.println(head);
                    }
                    head = head.next;
                }
            }
            System.out.println();
        }

        /**
         * orderDay sorts the Experiments in given day using accuracy.
         * @param day Sort day.
         */
        public void orderDay(int day)
        {
            System.out.println("orderDay(int day) method is called!");
            boolean flag = false;
            Experiment head = Head;
            Experiment[] array = new Experiment[1000];
            while(head != null)
            {
                if (head.getDay() == day)
                {
                    flag = true;
                    break;
                }
                head = head.nextDay;
            }
            Experiment nextDay = head.nextDay;
            if (nextDay != null && flag)
            {
                int k = 0;
                Experiment head1 = head;
                while(head.getDay() != nextDay.getDay())
                {
                    array[k] = new Experiment();
                    array[k].setSetup(head.getSetup());
                    array[k].setCompleted(head.getCompleted());
                    array[k].setAccuracy(head.getAccuracy());
                    array[k].setTime(head.getTime());
                    head = head.next;
                    ++k;
                }
                for (int i = 0; array[i] != null; i++)
                {
                    for (int j = 0; array[j] != null; j++)
                    {
                        if (array[i].getAccuracy() < array[j].getAccuracy())
                        {
                            Experiment temp = array[i];
                            array[i] = array[j];
                            array[j] = temp;
                        }
                    }
                }
                while(head1.getDay() != nextDay.getDay())
                {
                    head1.setTime(array[k-1].getTime());
                    head1.setAccuracy(array[k-1].getAccuracy());
                    head1.setCompleted(array[k-1].getCompleted());
                    head1.setSetup(array[k-1].getSetup());
                    head1 = head1.next;
                    --k;
                }
            }
            else
            {
                int k = 0;
                Experiment head1 = head;
                while(head != null)
                {
                    array[k] = new Experiment();
                    array[k].setSetup(head.getSetup());
                    array[k].setCompleted(head.getCompleted());
                    array[k].setAccuracy(head.getAccuracy());
                    array[k].setTime(head.getTime());
                    head = head.next;
                    ++k;
                }
                for (int i = 0; array[i] != null; i++)
                {
                    for (int j = 0; array[j] != null; j++)
                    {
                        if (array[i].getAccuracy() < array[j].getAccuracy())
                        {
                            Experiment temp = array[i];
                            array[i] = array[j];
                            array[j] = temp;
                        }
                    }
                }
                while(head1 != null)
                {
                    head1.setTime(array[k-1].getTime());
                    head1.setAccuracy(array[k-1].getAccuracy());
                    head1.setCompleted(array[k-1].getCompleted());
                    head1.setSetup(array[k-1].getSetup());
                    head1 = head1.next;
                    --k;
                }
            }
        }

        /**
         * orderExperiments sorts Experiments using accuracy and prints them.
         * orderExperiments doesn't damage the original list.
         */
        public void orderExperiments()
        {
            System.out.println("orderExperiments() method is called!");
            int k = 0;
            Experiment head = Head;
            Experiment[] array = new Experiment[1000];
            while(head != null)
            {
                array[k] = new Experiment();
                array[k].setSetup(head.getSetup());
                array[k].setCompleted(head.getCompleted());
                array[k].setAccuracy(head.getAccuracy());
                array[k].setTime(head.getTime());
                array[k].setDay(head.getDay());
                head = head.next;
                ++k;
            }
            for (int i = 0; array[i] != null; i++)
            {
                for (int j = 0; array[j] != null; j++)
                {
                    if (array[i].getAccuracy() < array[j].getAccuracy())
                    {
                        Experiment temp = array[i];
                        array[i] = array[j];
                        array[j] = temp;
                    }
                }
            }
            while(k > 0)
            {
                System.out.println(array[k-1]);
                --k;
            }
            System.out.println();
        }

        /**
         * Override iterator.
         * @return Returns the iterator.
         */
        @Override
        public Iterator<Experiment> iterator()
        {
            Iterator<Experiment> iterator = new Iterator<Experiment>()
            {
                /**
                 * Override hasNext of ExperimentList
                 * @return Returns if next is null or not.
                 */
                @Override
                public boolean hasNext() {
                    if (Head != null) {
                        return true;
                    } else {
                        return false;
                    }
                }

                /**
                 * Override next of ExperimentList
                 * @return Returns next experiment.
                 */
                @Override
                public Experiment next()
                {
                    if (hasNext())
                    {
                        Experiment temp = Head;
                        Head = Head.next;
                        return temp;
                    }
                    else
                    {
                        throw new NoSuchElementException();
                    }
                }
            };
            return iterator;
        }
    }

    public static class Experiment
    {
        private String setup;
        private int day;
        private String time;
        private boolean completed;
        private float accuracy;
        private Experiment next;
        private Experiment nextDay;

        public Experiment(){}

        /**
         * Experiment constructor.
         * @param setup User's setup.
         * @param day User's day.
         * @param time User's time.
         * @param completed User's completed.
         * @param accuracy User's accuracy.
         */
        public Experiment(String setup, int day, String time, boolean completed, float accuracy)
        {
            this.setup = setup;
            this.day = day;
            this.time = time;
            this.completed = completed;
            this.accuracy = accuracy;
        }

        /**
         * Returns day.
         * @return Returns day.
         */
        public int getDay() { return day; }

        /**
         * Returns setup.
         * @return Returns setup.
         */
        public String getSetup() { return setup; }

        /**
         * Returns time.
         * @return Returns time.
         */
        public String getTime(){ return time; }

        /**
         * Returns completed.
         * @return Returns completed.
         */
        public boolean getCompleted() { return completed; }

        /**
         * Return accuracy.
         * @return Return accuracy.
         */
        public float getAccuracy() { return accuracy; }

        /**
         * Sets setup.
         * @param data Sets setup.
         */
        public void setSetup(String data) { this.setup = data; }

        /**
         * Sets day.
         * @param data Sets day.
         */
        public void setDay(int data) { this.day = data; }

        /**
         * Sets time.
         * @param data Sets time.
         */
        public void setTime(String data){ this.time = data; }

        /**
         * Sets completed.
         * @param data Sets completed.
         */
        public void setCompleted(boolean data) { this.completed = data; }

        /**
         * Sets accuracy.
         * @param data Sets accuracy.
         */
        public void setAccuracy(float data){ this.accuracy = data; }

        /**
         * Override the toString to print Experiment object.
         * @return Returns attributes of experiment.
         */
        @Override
        public String toString() {
            return "Setup: "+this.getSetup()+"  Day: "+this.getDay()+" Accuracy: "+
                    this.getAccuracy()+" Time: "+this.getTime()+ " Completed : "+this.getCompleted();
        }
    }

    /**
     * Printing the linked list.
     * @param Head Printing the linked list.
     */
    public static void print(Experiment Head)
    {
        Experiment tempHead = Head;
        while(Head != null)
        {
            System.out.println(Head);
            Head = Head.next;
        }
        Head = tempHead;
        System.out.println();
    }

    /**
     * Test method of other methods.
     * @param args No use.
     */
    public static void main(String[] args)
    {
        // Calculating the time.
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        Date date = new Date();
        String time = dateFormat.format(date);

        // Creating Head and other Experiment objects.
        ExperimentList mainHead = new ExperimentList();
        mainHead.Head = new Experiment("Head",1,time,true,10f);
        Experiment m1 = new Experiment("Exp1",1,time,false,20f);
        Experiment m2 = new Experiment("Exp2",1,time,true,30f);
        Experiment m3 = new Experiment("Exp3",2,time,false,40f);
        Experiment m4 = new Experiment("Exp4",2,time,true,50f);
        Experiment m5 = new Experiment("Exp5",3,time,true,60f);
        Experiment m6 = new Experiment("Exp6",3,time,false,70f);
        Experiment m7 = new Experiment("Exp7",3,time,true,80f);
        Experiment m8 = new Experiment("Exp8",4,time,true,90f);
        Experiment m9 = new Experiment("Exp9",4,time,false,95f);
        Experiment m10 = new Experiment("Exp10",4,time,true,99f);

        // addExp method is running.
        System.out.println("addExp(Experiment E) method is called!");
        mainHead.addExp(m1);
        mainHead.addExp(m2);
        mainHead.addExp(m3);
        mainHead.addExp(m4);
        mainHead.addExp(m5);
        mainHead.addExp(m6);
        mainHead.addExp(m7);
        mainHead.addExp(m8);
        mainHead.addExp(m9);
        mainHead.addExp(m10);

        print(mainHead.Head);

        // orderExperiments method is running.
        mainHead.orderExperiments();

        // removeDay method is running.
        mainHead.removeDay(4);
        print(mainHead.Head);

        // removeExp method is running.
        // Index starts with 1.
        mainHead.removeExp(3,2);
        print(mainHead.Head);

        // getExp method is running.
        // Index starts with 1.
        System.out.println(mainHead.getExp(1,1));
        System.out.println();

        // orderDay method is running.
        mainHead.orderDay(1);
        print(mainHead.Head);

        // listExp method is running.
        mainHead.listExp(1);

        // setExp method is running.
        // Index starts with 1.
        Experiment m0 = new Experiment("NewExp",4,time,true,45f);
        mainHead.setExp(1,1,m0);
        print(mainHead.Head);

        // Initialize the iterator.
        // Print last version of linked list using iterator.
        System.out.println("Iterable printing is called!");
        Iterator iter = mainHead.iterator();
        while(iter.hasNext())
        {
            System.out.println(mainHead.Head);
            iter.next();
        }
    }
}
