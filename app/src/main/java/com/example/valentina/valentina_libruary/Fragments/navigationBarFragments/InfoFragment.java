package com.example.valentina.valentina_libruary.Fragments.navigationBarFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.valentina.valentina_libruary.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {

    @BindView(R.id.text)
    TextView text;

    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        ButterKnife.bind(this, view);
        text.setText("What does it mean when what you own is essential to who you are?" +
                "In our everyday grasp of owning things, we tag it materialism," +
                "consumerism, consumption. But I trust you’ll agree that the" +
                "possession of books is not identical to the possession of shoes:" +
                "Someone with a thousand books is someone you want to talk to; someone" +
                "with a thousand shoes is someone you suspect of belonging to the" +
                "Kardashian clan. Books are not objects in the same way that shoes" +
                "are objects. This is what Milton means in his sublime “Areopagitica,”" +
                "as necessary now as it was in 1644, when he asserted that “books are" +
                "not absolutely dead things, but do contain a potency of life in them" +
                "to be as active as that soul was whose progeny they are; nay, they do" +
                "preserve as in a vial the purest efficacy and extraction of that living" +
                "intellect that bred them.” …\n" +
                "\n" +
                "For many of us, our book collections are, in at least one major way," +
                "tantamount to our children—they are manifestations of our identity," +
                "embodiments of our selfhood; they are a dynamic interior heftily" +
                "externalized, a sensibility, a worldview defined and objectified." +
                "For readers, what they read is where they’ve been, and their collections" +
                "are evidence of the trek.");
        return view;
    }

}
